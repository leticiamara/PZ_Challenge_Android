package com.leticia.pz_challenge_android.presentation.presenter;

import com.leticia.pz_challenge_android.domain.model.Assets;
import com.leticia.pz_challenge_android.domain.model.MediaItem;
import com.leticia.pz_challenge_android.domain.repository.IAssetsRepository;
import com.leticia.pz_challenge_android.infrastucture.FileUtil;
import com.leticia.pz_challenge_android.infrastucture.SharedPreferencesManager;
import com.leticia.pz_challenge_android.presentation.mvpView.IMediaMvpView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaPresenter implements IMediaPresenter {

    private IAssetsRepository assetsRepository;
    private IMediaMvpView mvpView;
    private SharedPreferencesManager sharedPreferencesManager;

    public MediaPresenter(IMediaMvpView mvpView, IAssetsRepository assetsRepository) {
        this.mvpView = mvpView;
        this.assetsRepository = assetsRepository;
        sharedPreferencesManager = new SharedPreferencesManager(mvpView.getContext());
    }

    @Override
    public void loadAssetsList() {
        assetsRepository.getAssetsObservable().subscribe((Assets assets) -> {
            mvpView.showAssetsList(assets);
            sharedPreferencesManager.saveAssetsLocation(assets.getAssetsLocation());
        }, throwable -> mvpView.showErrorMessage(throwable.getMessage()));
    }

    @Override
    public void downloadData(MediaItem mediaItem, int position) {
        mvpView.startProgress(position);
        Observable<Object> videoObservable = getVideoObservable(mediaItem, position);
        Observable<Object> audioObservable = getAudioObservable(mediaItem, position);

        Observable.concat(videoObservable, audioObservable).subscribe(o -> {},
                throwable -> mvpView.showErrorMessage(throwable.getMessage()), () -> {
            mvpView.finishProgress(false, position);
            mvpView.showVideoScreen(mediaItem.getVideoStoredPath(), mediaItem.getAudioStorePath());
        });
    }

    @Override
    public List<MediaItem> fillMediaPathIfIsAlreadyDownloaded(List<MediaItem> mediaItems) {
        for (MediaItem mediaItem : mediaItems) {
            mediaItem.setVideoStoredPath(FileUtil.getFilePath(mediaItem.getVideoBackgroundName()));
            mediaItem.setAudioStorePath(FileUtil.getFilePath(mediaItem.getAudioName()));
        }
        return mediaItems;
    }

    private Observable<Object> getAudioObservable(MediaItem mediaItem, int position) {
        return assetsRepository.getDownloadFileObservable(sharedPreferencesManager.getAssetsLocation()
                    + FileUtil.SEPARATOR + mediaItem.getAudioName())
                    .flatMap(saveMediaToDisk(mediaItem.getAudioName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(file -> {
                        String audioPath = ((File) file).getAbsolutePath();
                        mvpView.updateAudioPath(audioPath, position);
                        mediaItem.setAudioStorePath(audioPath);
                    });
    }

    private Observable<Object> getVideoObservable(MediaItem mediaItem, int position) {
        return assetsRepository.getDownloadFileObservable(sharedPreferencesManager.getAssetsLocation()
                    + FileUtil.SEPARATOR + mediaItem.getVideoBackgroundName())
                    .flatMap(saveMediaToDisk(mediaItem.getVideoBackgroundName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(file -> {
                        String videoPath = ((File) file).getAbsolutePath();
                        mvpView.updateVideoPath(videoPath, position);
                        mediaItem.setVideoStoredPath(videoPath);
                    });
    }

    private Function<ResponseBody, ObservableSource<?>> saveMediaToDisk(String mediaName) {
        return responseBody -> saveFileToDisk(responseBody, mediaName);
    }

    private Observable<File> saveFileToDisk(final ResponseBody responseBody, String videoBackground) {
        return Observable.create(source -> {
            try {
                File file = FileUtil.saveFileToDisk(videoBackground, responseBody.source());
                source.onNext(file);
                source.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                source.onError(e);
            }
        });
    }
}

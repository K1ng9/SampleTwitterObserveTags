package com.example.vladkliuchak.sampleapp.data;


import com.example.vladkliuchak.sampleapp.data.data_tables.TweetDBTable;
import com.example.vladkliuchak.sampleapp.data.data_tables.UserDBTable;
import com.example.vladkliuchak.sampleapp.data.model.TweetUiModel;
import com.example.vladkliuchak.sampleapp.data.model.UserUiModel;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.queries.Query;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class DataBaseUsageManager {

    private final StorIOSQLite storIOSQLite;

    @Inject
    DataBaseUsageManager(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }


    public Observable<List<TweetUiModel>> getTweets() {
        return storIOSQLite
                .get()
                .listOfObjects(TweetUiModel.class)
                .withQuery(
                        Query.builder()
                                .table(TweetDBTable.Entry.TABLE_NAME)
                                .orderBy(TweetDBTable.Entry.COLUMN_CREATED_AT)
                                .build())
                .prepare()
                .asRxObservable();
    }
    public Observable<List<TweetUiModel>> getNotSyncTweets() {
        return storIOSQLite
                .get()
                .listOfObjects(TweetUiModel.class)
                .withQuery(Query.builder().table(TweetDBTable.Entry.TABLE_NAME)
                        .where(TweetDBTable.Entry.COLUMN_IS_TWEET_SYNC +" = ?")
                        .whereArgs(0) // 0 it's false
                        .build())
                .prepare()
                .asRxObservable();
    }
    public UserUiModel getUserById(String userId) {
        return storIOSQLite
                .get()
                .object(UserUiModel.class)
                .withQuery(
                        Query.builder()
                                .table(UserDBTable.Entry.TABLE_NAME)
                                .orderBy(UserDBTable.Entry.COLUMN_USER_ID)
                                .where(UserDBTable.Entry.COLUMN_USER_ID + " = ?")
                                .whereArgs(userId)
                                .build())
                .prepare()
                .executeAsBlocking();
    }

    public void addOrUpdateTweets(List<TweetUiModel> tweetUiModels) {
        storIOSQLite
                .put()
                .objects(tweetUiModels)
                .prepare()
                .executeAsBlocking();
    }

    public void addOrUpdateUsers(List<UserUiModel> userUiModels) {
        storIOSQLite
                .put()
                .objects(userUiModels)
                .prepare()
                .executeAsBlocking();
    }

    public Observable<PutResult> addOrUpdateTweetObservable(TweetUiModel tweetUiModel) {
        return storIOSQLite
                .put()
                .object(tweetUiModel)
                .prepare()
                .asRxObservable();
    }

    public void addOrUpdateTweetBlock(TweetUiModel tweetUiModel) {
        storIOSQLite
                .put()
                .object(tweetUiModel)
                .prepare()
                .executeAsBlocking();
    }
}

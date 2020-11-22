package org.maktab.broadcastreceiver.reository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.maktab.broadcastreceiver.dataBase.BroadCastLogDataBase;
import org.maktab.broadcastreceiver.dataBase.BroadCastLogDataBaseDAO;
import org.maktab.broadcastreceiver.model.BroadCastLog;

import java.util.ArrayList;
import java.util.List;

public class BroadCastLogsDBRepository implements IRepository {

    private static BroadCastLogsDBRepository sInstance;

    private BroadCastLogDataBaseDAO mLogDatabaseDAO;
    private List<BroadCastLog> mLogList;
    private Context mContext;
    private MutableLiveData<List<BroadCastLog>> mLiveDataLogcat;

    @Override
    public MutableLiveData<List<BroadCastLog>> getLiveDataLogcat() {
        return mLiveDataLogcat;
    }

    public static BroadCastLogsDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new BroadCastLogsDBRepository(context);

        return sInstance;
    }

    private BroadCastLogsDBRepository(Context context) {
        mContext = context.getApplicationContext();
        BroadCastLogDataBase logsDataBase = Room.databaseBuilder(mContext,
                BroadCastLogDataBase.class,
                "crime.db")
                .allowMainThreadQueries()
                .build();

        mLogList = new ArrayList<>();
        mLiveDataLogcat = new MutableLiveData<>();

        mLogDatabaseDAO = logsDataBase.getBroadCastLogDatabaseDAO();
    }

    @Override
    public void updateBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.updateBroadCastLog(broadCastLog);
    }

    @Override
    public void insertBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.insertBroadCastLog(broadCastLog);
        mLiveDataLogcat.postValue(mLogDatabaseDAO.getBroadCastLog());
    }

    @Override
    public void insertBroadCastLogs(BroadCastLog... broadCastLog) {
        mLogDatabaseDAO.insertBroadCastLog(broadCastLog);
    }

    @Override
    public void deleteBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.deleteBroadCastLog(broadCastLog);
    }

    @Override
    public List<BroadCastLog> getBroadCastLog() {
        return mLogDatabaseDAO.getBroadCastLog();
    }


}

package org.maktab.broadcastreceiver.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.maktab.broadcastreceiver.model.BroadCastLog;

@Database(entities = {BroadCastLog.class}, version = 1)
public abstract class BroadCastLogDataBase extends RoomDatabase {

    public abstract BroadCastLogDataBaseDAO getBroadCastLogDatabaseDAO();

}

package com.fitness.assistant.data.databases.daosimport androidx.lifecycle.LiveDataimport androidx.room.Daoimport androidx.room.Insertimport androidx.room.OnConflictStrategyimport androidx.room.Queryimport com.fitness.assistant.data.models.RecentProduct@Daointerface RecentDao {    @Insert(onConflict = OnConflictStrategy.REPLACE)    fun insertRecentProducts(recentProduct: RecentProduct)    /**     * return 10 recent products     */    @Query("select * from recentProducts order by rowid desc limit :maxRecentNumber")    fun getRecentProducts(maxRecentNumber: Int): LiveData<List<RecentProduct>>    @Query("delete from recentProducts")    fun deleteRecentProducts()    @Query("select count(rowid) from recentProducts")    fun getNumRecent(): Int    @Query("delete from recentProducts where name == (select name from recentProducts order by rowid asc limit 1)")    fun deleteFirstRecent()}
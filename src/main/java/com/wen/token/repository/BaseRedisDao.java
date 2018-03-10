package com.wen.token.repository;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface BaseRedisDao<K, V> {
    /**
     * @param date
     * @return
     */
    double getCreateTimeScore(long date);
    /**
     * @return
     */
    Set<K> getAllKeys();
    /**
     * @return
     */
    Map<K,V> getAllString();
    /**
     * @return
     */
    Map<K,Set<V>> getAllSet();
    /**
     * @return
     */
    Map<K,Set<V>> getAllZSetReverseRange();
    /**
     * @return
     */
    Map<K,Set<V>> getAllZSetRange();
    /**
     * 获取所有的List -key-value
     * @return
     */
    Map<K,List<V>> getAllList();
    /**
     * @return
     */
    Map<K,Map<K,V>> getAllMap();
    /**
     * @param key
     * @param objectList
     */
    void addList(K key, List<V> objectList);
    /**
     * @param key
     * @param obj
     * @return
     */
    long addList(K key,V obj);
    /**
     * @param key
     * @param obj
     * @return
     */
    long addList(K key,V ...obj);
    /**
     *
     * @param key
     * @param s
     * @param e
     * @return
     */
    List<V> getList(K key, long s, long e);
    /**
     * @param key
     */
    List<V> getList(K key);
    /**
     * @param key
     * @return
     */
    long getListSize(K key);
    /**

     * @param key
     * @param object
     * @return
     */
    long removeListValue(K key,V object);
    /**
     * @param key
     * @param object
     * @return
     */
    long removeListValue(K key,V... object);
    /**
     * @param keys
     */
    void remove(final K... keys);
    /**

     * @param key
     */
    void remove(final K key);
    /**
     *
     * @param key
     * @param s
     * @param e
     */
    void removeZSetRangeByScore(String key,double s , double e);
    /**
     * @param key
     * @param time
     * @return
     */
    Boolean setSetExpireTime(String key,Long time);
    /**
     * @param key
     * @param time
     * @return
     */
    Boolean setZSetExpireTime(String key,Long time);
    /**
     * @param key
     * @return
     */
    boolean exists(final K key);
    /**
     * @param key
     * @return
     */
    V get(final K key);
    /**
     * @param key
     * @return
     */
    List<V> get(final K... key);
    /**
     * @param regKey
     * @return
     */
    List<Object> getByRegular(final K regKey);
    /**
     * @param key
     * @param value
     */
    void set(final K key, V value);
    /**
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    void set(final K key, V value, Long expireTime);
    /**
     * @param key
     * @param expireTime
     * @return
     */
    boolean setExpireTime(K key, Long expireTime);
    /**
     * @param key
     * @return
     */
    DataType getType(K key);
    /**
     * @param key
     * @param field
     */
    void removeMapField(K key, V... field);
    /*
     * @param key
     * @return
     */
    Map<K,V> getMap(K key);
    /*
     * @param key
     * @return
     */
    Long getMapSize(K key);
    /**
     * @param key
     * @param field
     * @return
     */
    <T> T getMapField(K key, K field);
    /**
     * @param key
     * @return
     */
    Boolean hasMapKey(K key,K field);
    /**
     * @param key
     * @return
     */
    List<V> getMapFieldValue(K key);
    /**
     * @param key
     * @return
     */
    Set<V> getMapFieldKey(K key);
    /**
     * @param key
     * @param map
     */
    void addMap(K key, Map<K,V> map);
    /**
     * @param key
     * @param field
     * @param value
     */
    void addMap(K key, K field, Object value);
    /**
     * @param key
     * @param field
     * @param time
     * @param value
     */
    void addMap(K key, K field, V value,long time);
    /**
     * @param key
     * @param obj
     */
    void addSet(K key, V... obj);
    /**
     * @param key
     */
    void watch(String key);
    /**
     *
     * @param key
     * @param obj
     */
    long removeSetValue(K key, V obj);
    /**
     * @param key
     * @param obj
     */
    long removeSetValue(K key, V... obj);
    /**
     * @param key
     */
    long getSetSize(K key);
    /**
     * @param key
     */
    Boolean hasSetValue(K key,V obj);
    /**
     * @param key
     */
    Set<V> getSet(K key);
    /**
     * @param key
     * @param otherKey
     * @return
     */
    Set<V> getSetUnion(K key,K otherKey);
    /**
     * @param key
     * @param set
     * @return
     */
    Set<V> getSetUnion(K key,Set<Object> set);
    /**
     * @param key
     * @param otherKey
     * @return
     */
    Set<V> getSetIntersect(K key,K otherKey);
    /**
     * 获得set 交集
     * @param key
     * @param set
     * @return
     */
    Set<V> getSetIntersect(K key,Set<Object> set);
    /**
     * @param blears
     */
    void removeBlear(K... blears);
    /**
     * @param oldKey
     * @param newKey
     * @return
     */
    Boolean renameIfAbsent(String oldKey,String newKey);
    /**
     * @param blear
     */
    void removeBlear(K blear);
    /**
     * @param blears
     */
    void removeByRegular(String... blears);
    /**
     * @param blears
     */
    void removeByRegular(String blears);
    /**
     * @param key
     * @param blears
     */
    void removeMapFieldByRegular(K key,K... blears);
    /**
     * @param key
     * @param blear
     */
    void removeMapFieldByRegular(K key,K blear);
    /**
     * 移除key 对应的value
     * @param key
     * @param value
     * @return
     */
    Long removeZSetValue(K key, V... value);
    /**
     * @param key
     * @return
     */
    void removeZSet(K key);
    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    void removeZSetRange(K key,Long start,Long end);
    /**
     * @param key
     * @param key1
     * @param key2
     */
    void setZSetUnionAndStore(String key,String key1, String key2);
    /**
     * @param key
     */
    <T> T getZSetRange(K key);
    /**
     * @param key
     * @param start
     * @param end
     */
    <T> T getZSetRange(K key,long start,long end);
    /**
     * @param key
     */
    Set<Object> getZSetReverseRange(K key);
    /**
     * @param key
     * @param start
     * @param end
     */
    Set<V> getZSetReverseRange(K key,long start,long end);
    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<V> getZSetRangeByScore(String key, double start, double end);
    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<V> getZSetReverseRangeByScore(String key, double start, double end);
    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> getZSetRangeWithScores(K key, long start, long end);
    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> getZSetReverseRangeWithScores(K key, long start, long end);
    /**
     * @param key
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> getZSetRangeWithScores(K key);
    /**
     * @param key
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> getZSetReverseRangeWithScores(K key);
    /**
     * @param key
     * @param sMin
     * @param sMax
     * @return
     */
    long getZSetCountSize(K key,double sMin,double sMax);
    /**
     * @param key
     * @return
     */
    long getZSetSize(K key);
    /**
     * @param key
     * @param value
     * @return
     */
    double getZSetScore(K key,V value);
    /**
     * @param key
     * @param value
     * @param delta
     * @return
     */
    double incrementZSetScore(K key,V value,double delta);
    /**
     * @param key
     * @param score
     * @param value
     * @return
     */
    Boolean addZSet(String key ,double score, Object value);
    /**
     * @param key
     * @param value
     * @return
     */
    Long addZSet(K key,TreeSet<V> value);
    /**
     * @param key
     * @param score
     * @param value
     * @return
     */
    Boolean addZSet(K key,double[] score, Object[] value);
}
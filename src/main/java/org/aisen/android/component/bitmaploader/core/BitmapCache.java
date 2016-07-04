package org.aisen.android.component.bitmaploader.core;

import org.aisen.android.common.utils.KeyGenerator;
import org.aisen.android.component.bitmaploader.BitmapLoader;


public class BitmapCache {
    private LruMemoryCache<String, MyBitmap> mMemoryCache;
    private LruMemoryCache<String, MyBitmap> mSmallMemoryCache;//评论列表里的表情
    private LruMemoryCache<String, MyBitmap> mChatMemoryCache;//IM里的表情
    private LruMemoryCache<String, MyBitmap> mEmotionMemoryCache;//表情键盘

    public BitmapCache(int memCacheSize) {
        init(memCacheSize);
    }

    private void init(int memCacheSize) {
        mMemoryCache = new LruMemoryCache<String, MyBitmap>(memCacheSize) {
            @Override
            protected int sizeOf(String key, MyBitmap bitmap) {
                return BitmapCommonUtils.getBitmapSize(bitmap.getBitmap()) * 4;
            }
        };
        mSmallMemoryCache = new LruMemoryCache<String, MyBitmap>(memCacheSize) {
            @Override
            protected int sizeOf(String key, MyBitmap bitmap) {
                return BitmapCommonUtils.getBitmapSize(bitmap.getBitmap()) * 4;
            }
        };
        mChatMemoryCache = new LruMemoryCache<String, MyBitmap>(memCacheSize) {
            @Override
            protected int sizeOf(String key, MyBitmap bitmap) {
                return BitmapCommonUtils.getBitmapSize(bitmap.getBitmap()) * 4;
            }
        };
    }

    public void addBitmapToMemCache(String url, ImageConfig config, MyBitmap bitmap) {
        if (url == null || bitmap == null) {
            return;
        }

        if (mMemoryCache != null) {
            mMemoryCache.put(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)), bitmap);
        }
    }

    public MyBitmap getBitmapFromMemCache(String url, ImageConfig config) {
        if (mMemoryCache != null) {
            final MyBitmap memBitmap = mMemoryCache.get(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)));
            if (memBitmap != null) {
                return memBitmap;
            }
        }
        return null;
    }

    /**
     * 针对评论内容里的表情，size较小
     *
     * @param url
     * @param config
     * @param bitmap
     */
    public void addSmallBitmapToMemCache(String url, ImageConfig config, MyBitmap bitmap) {
        if (url == null || bitmap == null) {
            return;
        }

        if (mSmallMemoryCache != null) {
            mSmallMemoryCache.put(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)), bitmap);
        }
    }

    public MyBitmap getSmallBitmapFromMemCache(String url, ImageConfig config) {
        if (mSmallMemoryCache != null) {
            final MyBitmap memBitmap = mSmallMemoryCache.get(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)));
            if (memBitmap != null) {
                return memBitmap;
            }
        }
        return null;
    }

    /**
     * 针对评论内容里的表情，size较小
     *
     * @param url
     * @param config
     * @param bitmap
     */
    public void addChatBitmapToMemCache(String url, ImageConfig config, MyBitmap bitmap) {
        if (url == null || bitmap == null) {
            return;
        }

        if (mChatMemoryCache != null) {
            mChatMemoryCache.put(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)), bitmap);
        }
    }

    public MyBitmap getChatBitmapFromMemCache(String url, ImageConfig config) {
        if (mChatMemoryCache != null) {
            final MyBitmap memBitmap = mChatMemoryCache.get(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)));
            if (memBitmap != null) {
                return memBitmap;
            }
        }
        return null;
    }


    public void addEmotionToMemCache(String url, ImageConfig config, MyBitmap bitmap) {
        if (url == null || bitmap == null) {
            return;
        }

        if (mEmotionMemoryCache != null) {
            mEmotionMemoryCache.put(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)), bitmap);
        }
    }

    public MyBitmap getEmotionFromMemCache(String url, ImageConfig config) {
        if (mEmotionMemoryCache != null) {
            final MyBitmap memBitmap = mEmotionMemoryCache.get(KeyGenerator.generateMD5(BitmapLoader.getKeyByConfig(url, config)));
            if (memBitmap != null) {
                return memBitmap;
            }
        }
        return null;
    }

    public void clearMemCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
        }
        if (mSmallMemoryCache != null) {
            mSmallMemoryCache.evictAll();
        }
        if (mChatMemoryCache != null) {
            mChatMemoryCache.evictAll();
        }
        if (mEmotionMemoryCache != null) {
            mEmotionMemoryCache.evictAll();
        }
    }

    public void clearMemHalfCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictHalf();
        }
        if (mSmallMemoryCache != null) {
            mSmallMemoryCache.evictHalf();
        }
        if (mChatMemoryCache != null) {
            mChatMemoryCache.evictHalf();
        }
        if (mEmotionMemoryCache != null) {
            mEmotionMemoryCache.evictHalf();
        }
    }
}

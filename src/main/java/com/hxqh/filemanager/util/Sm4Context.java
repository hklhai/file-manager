package com.hxqh.filemanager.util;

/**
 * Created by Ocean lin on 2018/12/29.
 *
 * @author Ocean lin
 */
public class Sm4Context {
    public int mode;

    public int[] sk;

    public boolean isPadding;

    public Sm4Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new int[32];
    }
}

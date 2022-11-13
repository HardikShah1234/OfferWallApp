package com.harry.offerwallapp.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Network state whether loaded or loading.
 */

public final class NetworkState {
    @NotNull
    private final Status status;
    @NotNull
    private final String msg;
    @NotNull
    private static final NetworkState LOADED;
    @NotNull
    private static final NetworkState LOADING;
    @NotNull
    private static final NetworkState ERROR;
    @NotNull
    private static final NetworkState ENDLIST;
    @NotNull
    public static final NetworkState.Companion Companion = new NetworkState.Companion();

    @NotNull
    public final Status getStatus() {
        return this.status;
    }

    @NotNull
    public final String getMsg() {
        return this.msg;
    }

    public NetworkState(@NotNull Status status, @NotNull String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS, "Success");
        LOADING = new NetworkState(Status.RUNNING, "Running");
        ERROR = new NetworkState(Status.FAILED, "Something went wrong");
        ENDLIST = new NetworkState(Status.FAILED, "You have reached the end");
    }
    public static final class Companion {
        @NotNull
        public final NetworkState getLOADED() {
            return NetworkState.LOADED;
        }

        @NotNull
        public final NetworkState getLOADING() { return NetworkState.LOADING; }

        @NotNull
        public final NetworkState getERROR() {
            return NetworkState.ERROR;
        }

        @NotNull
        public final NetworkState getENDLIST() {
            return NetworkState.ENDLIST;
        }

        private Companion() {
        }
    }
}

package com.suhaili.gameinfoapp.core.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class AppExecutor constructor(
    private val DiskIO: Executor,
    private val NetworkThread: Executor,
    private val MainThread: Executor
) {

    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(4),
        MainThreadExecutor()
    )

    fun DiskIO() = this.DiskIO
    fun NetworkThread() = this.NetworkThread
    fun MainThread() = this.MainThread


    private class MainThreadExecutor : Executor {

        private val mainThread = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThread.post(command)
        }

    }
}
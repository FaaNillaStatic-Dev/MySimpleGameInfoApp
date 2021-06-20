package com.suhaili.gameinfoapp.core.util

import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity

interface GameListCallBack {

    fun itemCallBack(value: GameEntity)
}
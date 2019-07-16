package com.jorzet.truedareshot.presenters

/**
 * Created by Jorge Zepeda on 29/05/19.
 */

interface BasePresenter<V> {

    /**
     * Create and init presenter
     * @param view Class who implements its according view interface
     */
    fun create(view: V)

    /**
     * Destroy presenter instance and its dependencies
     */
    fun destroy()
}
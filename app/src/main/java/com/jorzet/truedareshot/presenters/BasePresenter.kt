package com.jorzet.truedareshot.presenters

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
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
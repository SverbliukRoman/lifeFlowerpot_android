package com.sverbusoft.lifeflowerpot.di.component

import com.sverbusoft.lifeflowerpot.managers.UserManager
import dagger.Component

@Component
interface LoginModelComponent {
    fun getModel(): UserManager
}
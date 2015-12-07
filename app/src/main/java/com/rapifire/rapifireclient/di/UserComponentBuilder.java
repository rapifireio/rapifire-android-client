package com.rapifire.rapifireclient.di;

import com.rapifire.rapifireclient.data.network.RapifireSession;
import com.rapifire.rapifireclient.di.components.UserComponent;

/**
 * Created by ktomek on 05.12.15.
 */
public interface UserComponentBuilder {
    UserComponent createUserComponent(RapifireSession session);
}

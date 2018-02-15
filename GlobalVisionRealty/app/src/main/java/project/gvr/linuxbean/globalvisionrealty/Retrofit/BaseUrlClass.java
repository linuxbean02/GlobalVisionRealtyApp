package project.gvr.linuxbean.globalvisionrealty.Retrofit;

import project.gvr.linuxbean.globalvisionrealty.Interfaces.GlobalService;

/**
 * Created by user on 2/13/2018.
 */

public class BaseUrlClass {

    public static final String BASE_URL = "";

    public static GlobalService globalservice() {
        return RetrofitClient.getClient(BASE_URL).create(GlobalService.class);
    }
}


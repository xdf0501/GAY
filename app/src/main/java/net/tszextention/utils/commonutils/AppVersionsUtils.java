package net.tszextention.utils.commonutils;

import android.content.Context;

/**
 * Created by Administrator on 15-12-14.
 */
public class AppVersionsUtils {
    public static class AppStruct {
        public String apkPackageName;
        public String versionName;
        public int versionCode;
    }

    public static AppStruct getPackageVersion(Context context) {
        try {
            String packageName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    packageName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
            AppStruct appStruct = new AppStruct();
            appStruct.apkPackageName = packageName;
            appStruct.versionName = versionName;
            appStruct.versionCode = versionCode;
            return appStruct;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

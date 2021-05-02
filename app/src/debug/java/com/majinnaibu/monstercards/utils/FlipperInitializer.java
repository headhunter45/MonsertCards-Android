package com.majinnaibu.monstercards.utils;


import android.content.Context;

import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin;
import com.facebook.soloader.SoLoader;
import com.majinnaibu.monstercards.BuildConfig;

public class FlipperInitializer {

    public static void init(Context ctx) {
        SoLoader.init(ctx, false);

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(ctx)) {
            final FlipperClient client = AndroidFlipperClient.getInstance(ctx);
            client.addPlugin(new InspectorFlipperPlugin(ctx, DescriptorMapping.withDefaults()));
            client.addPlugin(new DatabasesFlipperPlugin(ctx));
            client.addPlugin(NavigationFlipperPlugin.getInstance());
            client.start();
        }

    }

}
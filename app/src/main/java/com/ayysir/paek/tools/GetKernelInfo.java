/*
 * Copyright 2013 Fancy Updater (Boy Petersen & Parthipan Ramesh)
 * Copyright 2014 PAEK Updater Andre "ayysir" Saddler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ayysir.paek.tools;

import android.os.AsyncTask;

import com.ayysir.paek.helper.PaekHelper;

import java.util.ArrayList;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class GetKernelInfo extends AsyncTask<Object, Object, Object> {

    private List<String> mResult;

    @Override
    protected Object doInBackground(Object[] objects) {

        //CPU Frequency
        final String command = "cat /sys/devices/system/cpu/cpu0/cpufreq/";
        List<String> commands = new ArrayList<String>();
        commands.add(command + "scaling_available_frequencies"); // Available frequencies
        commands.add(command + "scaling_available_governors"); // Available governors
        commands.add(command + "scaling_min_freq"); // Min frequency
        commands.add(command + "scaling_max_freq"); // Max frequency
        commands.add(command + "screen_off_max_freq"); // Max Screen off frequency
        commands.add(command + "scaling_governor"); // Governor


        mResult = Shell.SU.run(commands);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        //CPU Frequency
        PaekHelper.available_frequencies = mResult.get(0).split(" ");
        PaekHelper.available_governors = mResult.get(1).split(" ");
        PaekHelper.min_freq = mResult.get(2);
        PaekHelper.max_freq = mResult.get(3);
        PaekHelper.screen_on_min = mResult.get(4);
    }
}
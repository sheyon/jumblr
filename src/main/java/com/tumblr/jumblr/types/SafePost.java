package com.tumblr.jumblr.types;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Save Post and not need to raise IOException
 * @author jc
 */
class SafePost extends Post {

    /**
     * save swallowing IOException (only for audio, video, photo)
     */
    @Override
    public void save() {
        try {
            super.save();
        }
        catch (ExecutionException | InterruptedException | IOException ex) {
            // No files involved, no IOException
        }

     }

}

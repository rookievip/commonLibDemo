package com.chen.api.ucrop.callback;

/**
 * Interface for crop bound change notifying.
 */
public interface CropBoundsChangeListener {

    void onCropAspectRatioChanged(float cropRatio);

}
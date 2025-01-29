//import default react library
import React from "react";

//import custom react component
import CameraViewContainer from "./view_options/camera_view";
import TrainDataContainer from "./view_options/train_data_view";

//create and export default main left side container
export default function MainLeftSideContainer() {
    //return created ui component
    return (
        <>
            <CameraViewContainer/>
            <TrainDataContainer/>
        </>
    );
}
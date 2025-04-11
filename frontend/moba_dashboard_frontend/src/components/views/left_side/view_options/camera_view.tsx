//import default react library
import React from "react";

//import custom react component
import { ContentContainer } from "../../../container/content_container";
import { VideoViewComponent } from "../../../other/video_view";
import FlexBox from "../../../container/FlexBox";
import Image from "../../../atoms/images";
import Text from "../../../atoms/texts";

//create and export default camera view container
export default function CameraViewContainer() {
	//return created ui component
	return (
		<>
			<ContentContainer contentContainerHeaderText="Kameraüberwachung">
				<VideoViewComponent style={{ marginTop: "4%" }} />
			</ContentContainer>
		</>
	);
}

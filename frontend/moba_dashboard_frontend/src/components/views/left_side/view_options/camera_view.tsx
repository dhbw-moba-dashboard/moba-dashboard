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
				<FlexBox style={{justifyContent: 'space-between', alignItems: 'center'}}>
					<div>
						<VideoViewComponent style={{ marginTop: "4%" }} />
					</div>
					{
						//check if to show live image/ text information
						true && 					<div style={{marginLeft: '15%'}}>
						<Image style={{height: '60px', width: 'auto'}} imageValue="images/color/Icon_Live_Color.png"/>
						<Text style={{fontWeight: 'bold', fontSize: '32px'}} textValue="Live"/>
					</div>
					}
				</FlexBox>
			</ContentContainer>
		</>
	);
}

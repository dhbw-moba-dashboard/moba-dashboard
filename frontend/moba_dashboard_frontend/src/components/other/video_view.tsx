//import react library
import React, { useRef, useState, useCallback, useEffect } from "react";
import type Webcam from "react-webcam";

//import css styling file
import "../../style/components_style/other/other_style.css";

//import default component properties
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//import custom rs function
import { setConsoleMessage } from "../../logic/tools/messages";
import { ImageLink } from "../atoms/texts";

//create and export video view component
export const VideoViewComponent: React.FC<DefaultComponentProps> = (
	props: DefaultComponentProps,
) => {
	//create webcam ref
	const webcamRef = useRef<Webcam>(null);
	//define video view action button properties
	const [actionButtonState, setActionButtonState] = useState<boolean>(false);
	const [videoViewActionButtonProps, setVideoViewActionButtonProps] = useState<
		string[]
	>(["Streaming Starten", "Icon_Play_IOS_Primary_Color"]);

	const capture = React.useCallback(() => {
		const imageSrc = webcamRef.current?.getScreenshot();
		//check if image source is set
		if (imageSrc) {
			//set image source to console
			setConsoleMessage(imageSrc);
		}
	}, [webcamRef]);

	//function for click action
	const videoStreamAction = () => {
		const newState: boolean = !actionButtonState;
		setActionButtonState(newState);
		setVideoViewActionButtonProps(
			newState
				? ["Streaming Stoppen", "Icon_Pause_IOS_Primary_Color"]
				: ["Streaming Starten", "Icon_Play_IOS_Primary_Color"],
		);
	};

	//return created react component
	return (
		<>
			<div
				id={props.id}
				className={`video-view-style-properties ${props.className}`}
				style={props.style}
			>
				loading
			</div>
			<div style={{ marginTop: "-1.5%" }}>
				<ImageLink
					textValue={videoViewActionButtonProps[0]}
					textImage={`images/general/${videoViewActionButtonProps[1]}.png`}
					textLinkAction={videoStreamAction}
				/>
			</div>
		</>
	);
};

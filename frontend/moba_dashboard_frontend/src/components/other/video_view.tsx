//import react library
import React, { useRef, useState, useCallback, useEffect } from "react";
import type Webcam from "react-webcam";

//import css styling file
import "../../style/components_style/other/other_style.css";

//import default component properties
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//import custom rs function
import { setConsoleMessage } from "../../logic/tools/messages";
import Text, { ImageLink } from "../atoms/texts";

//create and export video view component
export const VideoViewComponent: React.FC<DefaultComponentProps> = (
	props: DefaultComponentProps,
) => {
	//create webcam ref
	const webcamRef = useRef<HTMLVideoElement | null>(null);
	//store media stream reference
	const streamRef = useRef<MediaStream | null>(null);

	//define video view action button properties
	const [actionButtonState, setActionButtonState] = useState<boolean>(false);
	const [videoViewActionButtonProps, setVideoViewActionButtonProps] = useState<
		string[]
	>(["Streaming Starten", "Icon_Play_IOS_Primary_Color"]);

	//function for click action
	const videoStreamAction = async (): Promise<void> => {
		const newState: boolean = !actionButtonState;
		setActionButtonState(newState);

		//start or stop the webcam based on newState
		if (newState) {
			//start webcam
			try {
				const stream = await navigator.mediaDevices.getUserMedia({ video: true });
				streamRef.current = stream;
				if (webcamRef.current) {
					webcamRef.current.srcObject = stream;
				}
			} catch (videoWebcamError: any) {
				setConsoleMessage(`Error accessing webcam: ${videoWebcamError}`, true);
				setActionButtonState(false); // Reset button state if an error occurs
				return;
			}
		} else {
			//stop webcam
			if (streamRef.current) {
				streamRef.current.getTracks().forEach((track) => track.stop());
				streamRef.current = null;
			}
			if (webcamRef.current) {
				webcamRef.current.srcObject = null;
			}
		}

		// Update button text and icon
		setVideoViewActionButtonProps(newState ?
			["Streaming Stoppen", "Icon_Pause_IOS_Primary_Color"]
			: ["Streaming Starten", "Icon_Play_IOS_Primary_Color"]
		);
	};

	//return created react component
	return (
		<>
			<div>
				{
					//check if to show video stream or placeholder information
					actionButtonState ?
						<video ref={webcamRef} autoPlay playsInline className='video-view-style-properties'/>
						: <div style={{height: '250px'}}>
							<Text textValue="Überwachung nicht gestartet!"/>
						</div>
				}
			</div>
			<div style={{marginTop: "-1.5%"}}>
				<div onClick={videoStreamAction}>
				<ImageLink textValue={videoViewActionButtonProps[0]} textImage={`images/general/${videoViewActionButtonProps[1]}.png`}/>
				</div>
			</div>
		</>
	);
};

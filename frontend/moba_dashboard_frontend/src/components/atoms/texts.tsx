//import libraries
import type React from "react";

//import css file
import "../../style/components_style/atoms/texts.css";

//import custom react files
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//import custom react components
import FlexBox from "../container/FlexBox";
import Image from "./images";

//define interface for text properties
interface TextProperties extends DefaultComponentProps {
	textValue: string;
	textLinkUrl?: string | undefined;
	textImage?: string | undefined;
	textStyle?: React.CSSProperties;
	imageStyle?: React.CSSProperties;
	textLinkAction?: (value: any) => void;
}

//create text component
const Text: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<span id={props.id} style={props.style} className={props.className}>
			{props.textValue}
		</span>
	);
};
//export default text component
export default Text;

//create and export header text component
export const HeaderText: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<Text
			id={props.id}
			style={props.style}
			className="header-text-component-style-properties"
			textValue={props.textValue}
		/>
	);
};

//create and export image text component
export const ImageText: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<FlexBox style={props.style}>
			<Image
				style={{ height: "18px", width: "auto", ...props.imageStyle }}
				imageValue={props.textImage}
			/>
			<Text
				style={{ marginLeft: "1%", ...props.textStyle }}
				textValue={props.textValue}
			/>
		</FlexBox>
	);
};

//create and export text link component
export const TextLink: React.FC<TextProperties> = (props: TextProperties) => {
	//function to execute onclick event
	function executeOnClickEvent() {
		//check if link is set
		if (props.textLinkUrl) {
			window.open(
				!props.textLinkUrl.includes("https://")
					? `https://${props.textLinkUrl}`
					: props.textLinkUrl,
				"_blank",
			);
		} else if (props.textLinkAction) {
			//execute action
			props.textLinkAction;
		}
	}

	//return created component
	return (
		<a
			id={props.id}
			style={props.style}
			className={props.className}
			onClick={executeOnClickEvent}
		>
			{props.textValue}
		</a>
	);
};

//create and export image text link component
export const ImageLink: React.FC<TextProperties> = (props: TextProperties) => {
	//return created component
	return (
		<FlexBox id={props.id}>
			<Image
				style={{ height: "18px", width: "auto", marginRight: "1%" }}
				imageValue={props.textImage}
			/>
			<TextLink
				style={props.style}
				className={props.className}
				textValue={props.textValue}
				textLinkUrl={props.textLinkUrl}
				textLinkAction={props.textLinkAction}
			/>
		</FlexBox>
	);
};

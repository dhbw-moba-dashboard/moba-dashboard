//import libraries
import type React from "react";

//import css file
import "../../style/components_style/atoms/texts.css";

//import custom react components
import FlexBox from "../container/FlexBox";
import Image from "./images";

//define interface for text properties
interface TextProperties {
	textID?: string | undefined;
	textValue: string;
	textLinkUrl?: string | undefined;
	textImage?: string | undefined;
	style?: React.CSSProperties;
	className?: string | undefined;
}

//create text component
const Text: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<span id={props.textID} style={props.style} className={props.className}>
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
			textID={props.textID}
			style={props.style}
			className="header-text-component-style-properties"
			textValue={props.textValue}
		/>
	);
};

//create and export text link component
export const TextLink: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<a id={props.textID} style={props.style} className={props.className}>
			{props.className}
		</a>
	);
};

//create and export image text link component
export const ImageLink: React.FC<TextProperties> = (props: TextProperties) => {
	//function to execute onclick event
	function executeOnClickEvent() {
		//check if link is set
		if (props.textLinkUrl) {
			window.open(
				!props.textValue.includes("https://")
					? `https://${props.textLinkUrl}`
					: props.textLinkUrl,
				"_blank",
			);
		}
	}

	//return created component
	return (
		<FlexBox id={props.textID} clickAction={executeOnClickEvent}>
			<Image
				style={{ height: "18px", width: "auto", marginRight: "2%" }}
				imageValue={props.textImage}
			/>
			<TextLink
				style={props.style}
				className={props.className}
				textValue={props.textValue}
			/>
		</FlexBox>
	);
};

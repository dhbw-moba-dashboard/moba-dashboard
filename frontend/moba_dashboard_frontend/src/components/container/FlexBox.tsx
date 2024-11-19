//import libraries
import type React from "react";

//import css file
import "../../style/components_style/container/container.css";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//define interface for FlexBox
interface FlexBoxProps extends DefaultComponentProps {
	clickAction?: (value?: any) => void;
}

//create default flexbox component
const FlexBox: React.FC<FlexBoxProps> = (props: FlexBoxProps) => {
	return (
		<div
			id={props.id}
			className={`flexbox-style-properties ${props.className}`}
			style={props.style}
			onClick={props.clickAction}
		>
			{props.children}
		</div>
	);
};
//define default FlexBox
export default FlexBox;

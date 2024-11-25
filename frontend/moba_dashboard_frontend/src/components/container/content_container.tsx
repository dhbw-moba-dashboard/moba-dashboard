//import libraries
import type React from "react";

//import css file
import "../../style/components_style/container/container.css";
import { HeaderText } from "../atoms/texts";

//import custom react files
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//define interface for content container properties
interface ContentContainerProperties extends DefaultComponentProps {
	contentContainerHeaderText: string;
	className?: string | undefined;
}

//create and export content container component
export const ContentContainer: React.FC<ContentContainerProperties> = (
	props: ContentContainerProperties,
) => {
	return (
		<div
			id={props.id}
			className={`content-container-style-properties ${props.className}`}
			style={props.style}
		>
			<HeaderText textValue={props.contentContainerHeaderText} />
			{props.children}
		</div>
	);
};

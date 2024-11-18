//import libraries
import type React from "react";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//import css file
import "../../style/components_style/atoms/images.css";

//define interface for image properties
interface ImageProperties extends DefaultComponentProps {
	imageValue: string | undefined;
	imageAltText?: string | undefined;
}

//create image component
const Image: React.FC<ImageProperties> = (props: ImageProperties) => {
	return (
		<img
			id={props.id}
			style={props.style}
			className={props.className}
			src={props.imageValue}
			alt={props.imageAltText}
		/>
	);
};
//define default image component
export default Image;

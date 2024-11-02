//import libraries
import React from "react";

//import css file
import '../../style/components_style/atoms/images.css';

//define interface for image properties
interface ImageProperties {
    imageID?: string | undefined,
    imageValue: string | undefined,
    imageAltText?: string | undefined,
    style?: React.CSSProperties,
    className?: string | undefined
}

//create image component
const Image: React.FC<ImageProperties> = (props: ImageProperties) => {
    return (
        <img id={props.imageID} style={props.style} className={props.className} src={props.imageValue} alt={props.imageAltText}/>
    );
}
//define default image component
export default Image;
//import libraries
import type React from "react";

//import css file
import "../../style/components_style/container/container.css";
import { GlassMorphButton } from "../atoms/buttons";
import Image from "../atoms/images";
import Text from "../atoms/texts";

//import custom react files
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//define interface for train option element props
interface TrainOptionElementProps extends DefaultComponentProps {
	trainOptionElementText: string;
	trainOptionElementImage?: string | undefined;
	style?: React.CSSProperties;
	action?: (value?: any) => void;
}

//define train option element
export const TrainOptionElement: React.FC<TrainOptionElementProps> = (
	props: TrainOptionElementProps,
) => {
	//return created train option element
	return (
		<GlassMorphButton buttonAction={props.action} style={props.style}>
			<Image
				style={{ height: "45px", width: "auto" }}
				imageValue={props.trainOptionElementImage}
			/>
			<div style={{ marginTop: "4%" }}>
				<Text
					style={{ fontWeight: "bold", fontSize: "18px" }}
					textValue={props.trainOptionElementText}
				/>
			</div>
		</GlassMorphButton>
	);
};

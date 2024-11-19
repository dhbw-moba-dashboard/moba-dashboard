//import libraries
import type React from "react";

//import css file
import '../../style/components_style/container/container.css';
import {GlassMorphButton} from "../atoms/buttons";
import Image from "../atoms/images";
import Text from "../atoms/texts";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//define interface for train option element props
interface TrainOptionElementProps extends DefaultComponentProps {
    trainOptionElementText: string,
    trainOptionElementImage?: string | undefined,
    action?: (value?: any) => void
}

//define train option element
export const TrainOptionElement: React.FC<TrainOptionElementProps> = (props: TrainOptionElementProps) => {
    //function to select train option element
    function selectTrainOption() {

    }

    //return created train option element
    return (
        <GlassMorphButton id={props.id} buttonAction={props.action}>
            <Image style={{height: '40px', width: 'auto'}} imageValue={props.trainOptionElementImage}/>
            <Text style={{fontSize: '20px', fontWeight: 'bold'}} textValue={props.trainOptionElementText}/>
        </GlassMorphButton>
    );
}
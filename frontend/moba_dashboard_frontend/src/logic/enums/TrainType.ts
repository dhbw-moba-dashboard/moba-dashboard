//define export train types
export enum TrainType {
	STEAM = "steam",
	ELECTRIC = "electric",
}

//function to set default image if no specific image is set
export function setDefaultImage(
	trainType: TrainType,
	passedTrainImage?: string | undefined,
): string {
	//define map with default train images
	const trainImages = new Map<TrainType, string>([
		[TrainType.STEAM, "Steam_Train"],
		[TrainType.ELECTRIC, "Electric_Train"],
	]);

	//return train image
	return passedTrainImage
		? passedTrainImage
		: `images/trains/default/${trainImages.get(trainType)}.png`;
}

//define and export function to fetch train information
export async function fetchTrainInformation(trainId: string): Promise<any[]> {
    //define backend url
    const TRAIN_INFORMATION_URL: string =
        `${process.env.REACT_APP_PROJECT_BACKEND_URL}/`;

    //make backend call to load data
    let backendResponse: Response = await fetch(TRAIN_INFORMATION_URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });
    //check if response is not ok to set error message
    if(!backendResponse.ok) {
        //set error message
        throw new Error('error fetching train information');
    }
    //return received information data
    return backendResponse.json();
}
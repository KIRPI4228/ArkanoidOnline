import {get} from "./Rest";

export async function isUserAuthorized() {
    const response = await get("/user/auth/is-authorized")
        .catch(error => {
            console.log(error);
        });

    if (response === undefined || typeof response.data != "boolean") {
        return undefined;
    }

    return response.data;
}
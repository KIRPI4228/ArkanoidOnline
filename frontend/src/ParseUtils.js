export function parseTranslation(text, t) {
    const peaces = text.split("|");

    if (peaces.length === 2) {
        console.log(peaces[1]);
        return t(peaces[0], JSON.parse(peaces[1]));
    }

    return t(peaces[0]);
}
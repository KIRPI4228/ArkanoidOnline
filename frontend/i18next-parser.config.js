module.exports = {
    defaultNamespace: 'translation',
    lexers: {
        js: ['JsxLexer'],
        default: ['JavascriptLexer']
    },
    locales: ['en', 'ru'],
    output: 'public/locales/$LOCALE/$NAMESPACE.json',
    input: ['src/**/*.js']
}
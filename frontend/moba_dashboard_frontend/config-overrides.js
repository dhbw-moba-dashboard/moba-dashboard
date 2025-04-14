module.exports = function override(config, env) {
    const fallback = config.resolve.fallback || {};
    Object.assign(fallback, {
        crypto: false, // require.resolve("crypto-browserify") can be polyfilled here if needed
        stream: false, // require.resolve("stream-browserify") can be polyfilled here if needed
        assert: false, // require.resolve("assert") can be polyfilled here if needed
        http: false, // require.resolve("stream-http") can be polyfilled here if needed
        https: false, // require.resolve("https-browserify") can be polyfilled here if needed
        os: require.resolve("os-browserify"),
        path: require.resolve("path-browserify"),
        url: false, // require.resolve("url") can be polyfilled here if needed
        zlib: false, // require.resolve("browserify-zlib") can be polyfilled here if needed
    });
    config.resolve.fallback = fallback;
    return config;
}
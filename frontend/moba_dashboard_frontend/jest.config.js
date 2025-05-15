module.exports = {
    transform: {
        "^.+\\.(ts|tsx)$": "babel-jest",
    },
    testEnvironment: "jsdom",
    moduleFileExtensions: ["ts", "tsx", "js", "jsx"],
    setupFilesAfterEnv: ["@testing-library/jest-dom"],
};

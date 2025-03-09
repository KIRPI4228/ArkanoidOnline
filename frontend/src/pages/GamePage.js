import {Unity, useUnityContext} from "react-unity-webgl";
import {useEffect, useState} from "react";

const buildPath = "Build/";

const GamePage = () => {
    const [isStarted, setIsStarted] = useState(false);

    const { unityProvider, addEventListener } = useUnityContext({
        loaderUrl: buildPath + "Build.loader.js",
        dataUrl: buildPath + "Build.data",
        frameworkUrl: buildPath + "Build.framework.js",
        codeUrl: buildPath + "Build.wasm",
    });

    useEffect(() => {
        addEventListener("OnStarted", () => {
            setIsStarted(true);
        });
    }, [addEventListener]);

    return (
        <>
            <div style={{
                height: "100%",
                width: "100%",
                display: "flex",
                justifyContent: "center"
            }}>
                <Unity unityProvider={unityProvider} style={{
                    width: (isStarted ? "100%" : "1px"),
                    height: (isStarted ? "100%" : "1px"),
                    marginLeft: "auto",
                    marginRight: "auto",
                }}/>
            </div>
        </>
    );
}

export default GamePage;
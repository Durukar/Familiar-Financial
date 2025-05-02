/* eslint-disable @typescript-eslint/no-unsafe-function-type */
/*
 * Executa uma função uma única vez, não import se mudar os parâmetros ou até
 * mesmo a própria função.
 */

import { useEffect, useRef } from "react";

function useOnce(callback: Function, params: unknown[] = []) {
  const callbackRef = useRef(callback);
  const paramsRef = useRef(params);

  useEffect(() => {
    callbackRef.current(paramsRef.current);
  }, []);
}

export { useOnce };

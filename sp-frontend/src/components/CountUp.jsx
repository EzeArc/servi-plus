import { useProgressiveNumber } from "@/hooks/useProgressiveNumber";
import { useEffect } from "preact/hooks";

export const CountUp = ({ initial, final, decimals, duration }) => {
  const [count, setCount] = useProgressiveNumber(initial, duration, decimals);

  useEffect(() => {
    setCount(final);
  }, [final]);

  return <span>{count}</span>;
};

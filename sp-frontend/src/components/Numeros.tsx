import apiRoutes from "@/api";
import { CountUp } from "@/components/CountUp"
import NumerosItem from "@/components/NumerosItem"
import { useEffect, useRef, useState } from "preact/hooks"

async function fetchData() {
  const dataUsers = await fetch(`${apiRoutes.serviPlus}/totalUsers`).then(
    (response) => response.json()
  );
  const dataProviders = await fetch(`${apiRoutes.serviPlus}/totalProviders`).then(
    (response) => response.json()
  );

  const dataJobs = await fetch(`${apiRoutes.serviPlus}/totalJobs`).then(
    (response) => response.json()
  );

  return { dataUsers, dataProviders, dataJobs };
}

async function main() {
  const data = await fetchData();
  console.log(data);
}

export default async function Numeros({i18n, metricas}:{i18n:any , metricas: string}) {
  const [metrics, setMetrics] = useState({usuarios:0,provedores:0,trabajos:0})
  const [isIntersecting, setIsIntersecting] = useState(false)


  useEffect(() => {
    if(!isIntersecting) return
    const _metric = main[Number(metricas)-1].metrics
    setMetrics(_metric)
  },[isIntersecting])

  const numeros = useRef<HTMLDivElement>(null)

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.intersectionRatio > 0) {
          setIsIntersecting(true)
        }
      }
    )
    if (!numeros.current) return
 
    observer.observe(numeros.current)

    return () => {
      if (!numeros.current) return
      observer.unobserve(numeros.current)
    }
  }, [])

  return (
    <section class="max-w-6xl mx-auto py-20 px-20">
      <h2
        class="text-4xl lg:text-6xl font-tomaso text-center text-balance mb-10 lg:mb-20"
      >
        {i18n.ARCHIVO.COUNTER_TITLE}
      </h2>
      {
        <div ref={numeros} class={`grid grid-cols-1 lg:grid-cols-3 gap-y-10`}>
        <NumerosItem title={i18n.ARCHIVO.COUNTER_VIEWS}>
          <CountUp initial={0} final={metrics.usuarios ?? 0} decimals={1} />M
        </NumerosItem>
        <NumerosItem title={i18n.ARCHIVO.COUNTER_NEWS}>
          <CountUp  initial={0} final={metrics.provedores ?? 0} />
        </NumerosItem>
        <NumerosItem title={i18n.ARCHIVO.COUNTER_MEDIA}>
          <CountUp  initial={0} final={metrics.trabajos ?? 0} decimals={1} />M€
        </NumerosItem>
      </div>
      }
    </section>
  )
}
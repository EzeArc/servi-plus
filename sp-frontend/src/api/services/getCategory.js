const DEFAULT_CATEGORY_PARAM = "1";

export const GET = ({ request }) => {
  const { url } = request;
  const searchParams = new URL(url).searchParams;
  const category = Number(
    searchParams.get("category") ?? DEFAULT_CATEGORY_PARAM
  );
  const categoryInfo = votesInfo[category];

  return new Response(JSON.stringify(categoryInfo));
};

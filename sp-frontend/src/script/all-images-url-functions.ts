// Max edición 1 = 74
// Max edicion 2 = 108

type Edition = "1" | "2";

const mc = "/assets/1.3b147429.jpg",
  bc = "/assets/10.8e48e7e1.jpg",
  vc = "/assets/11.cf829d1a.jpg",
  yc = "/assets/12.a39430bd.jpg",
  Ac = "/assets/13.a2bdc9f0.jpg",
  wc = "/assets/14.997ff306.jpg",
  jc = "/assets/15.a7e625c2.jpg",
  Sc = "/assets/16.a7a313b2.jpg",
  xc = "/assets/17.84b323b2.jpg",
  Ec = "/assets/18.915b9e1d.jpg",
  Cc = "/assets/19.fa55b1fd.jpg",
  Tc = "/assets/2.5e798bd8.jpg",
  Mc = "/assets/20.ef9e4552.jpg"

export function getThumbURL(photo: string | number, edition: Edition) {
  return new URL(
    Object.assign({
      "/assets/gallery/1/thumbs/1.jpg": mc,
      "/assets/gallery/1/thumbs/10.jpg": bc,
      "/assets/gallery/1/thumbs/11.jpg": vc,
      "/assets/gallery/1/thumbs/12.jpg": yc,
      "/assets/gallery/1/thumbs/13.jpg": Ac,
      "/assets/gallery/1/thumbs/14.jpg": wc,
      "/assets/gallery/1/thumbs/15.jpg": jc,
      "/assets/gallery/1/thumbs/16.jpg": Sc,
      "/assets/gallery/1/thumbs/17.jpg": xc,
      "/assets/gallery/1/thumbs/18.jpg": Ec,
      "/assets/gallery/1/thumbs/19.jpg": Cc,
    })[`/assets/gallery/${edition}/thumbs/${photo}.jpg`],
    "https://premiosesland.com/"
  );
}

export function getImageURL(photo: string | number, edition: Edition) {
  return new URL(
    Object.assign({


    })[`/assets/gallery/${edition}/${photo}.jpg`],
    "https://premiosesland.com/"
  );
}


const xS = "/assets/baile1.b9af3586.jpg"
  , ES = "/assets/baile2.adcdf1d8.jpg"
  , CS = "/assets/cancion1.4de07f2c.jpg"
  , TS = "/assets/cancion2.27d9cb4c.jpg"
  , MS = "/assets/caster1.175e1378.jpg"
  , PS = "/assets/caster2.bcaa64fd.jpg"
export function getAwardImageURL(photo: string | number, edition: Edition) {
  return new URL(Object.assign({
    "/assets/palmares/1/baile1.jpg": xS,
    "/assets/palmares/1/baile2.jpg": ES,
    "/assets/palmares/1/cancion1.jpg": CS,
    "/assets/palmares/1/cancion2.jpg": TS,
    "/assets/palmares/1/caster1.jpg": MS,
  })[`/assets/palmares/${edition}/${photo}.jpg`], 'https://premiosesland.com')
}

export function getMobileAwardImageURL(photo: string | number, edition: Edition) {
  return new URL(Object.assign({
  })[`/assets/palmares/${edition}/m_${photo}.jpg`], "https://premiosesland.com")
}
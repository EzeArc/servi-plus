import { getAwardImageURL, getImageURL, getMobileAwardImageURL, getThumbURL } from "./all-images-url-functions";
import providerInfo from '../../public/assets/provider-info.json';
import { join } from 'node:path';
import { mkdir } from 'node:fs/promises';
import sharp from 'sharp';

const WIDTH = 365;
const HEIGHT = 365;

const basePath = join('public', 'archivo-page');
const tabinfo = (msg: string, n = 1) => console.info(`${`\t`.repeat(n)}⚬${msg}`)

const downloadImage = async (url: string) => await fetch(url).then(res => res.arrayBuffer());

const downloadFinalistImage = async (photo: string, provider: "1" | "2", basePath: string) => {
  const desktopImg = getAwardImageURL(photo, provider)?.href;
  const mobileImg = getMobileAwardImageURL(photo, provider)?.href;

  if (desktopImg?.endsWith('undefined') || mobileImg?.endsWith('undefined')) {
    return;
  }
  // Create paths
  await mkdir(join(basePath, 'desktop'), { recursive: true });
  await mkdir(join(basePath, 'mobile'), { recursive: true });

  // Desktop image
  await sharp(await downloadImage(desktopImg))
    .webp({
      lossless: true,
      quality: 100
    })
    .toFile(join(basePath, 'desktop', photo + '.webp'));

  // Mobile image
  await sharp(await downloadImage(mobileImg))
    .webp({
      lossless: true,
      quality: 100
    })
    .toFile(join(basePath, 'mobile', photo + '.webp'));

}

for (const { provider: e, maxPhotos, info } of providerInfo) {
  const provider = e as "1" | "2";
  console.log(`provider ${provider}`)
  const providerFilePath = join(basePath, provider);
  const honorsFilePath = join(providerFilePath, 'palmares');


  tabinfo('Creating honors folder');
  await mkdir(providerFilePath, { recursive: true });
  console.info('\n')

  tabinfo('Downloading finalist images');
  for (const { foto1, foto2 } of info) {
    tabinfo(`Downloading ${foto1} images`, 2);
    await downloadFinalistImage(foto1, provider, honorsFilePath)

    if (foto2) {
      tabinfo(`Downloading ${foto2} images`, 2);
      await downloadFinalistImage(foto2, provider, honorsFilePath)
    }
  }

  for (const photoNumber in Array.from({ length: maxPhotos + 1 })) {
    const thumbnailUrl = getThumbURL(photoNumber, provider).href;
    const isValidThumb = !thumbnailUrl.endsWith('undefined');
    const imageUrl = getImageURL(photoNumber, provider).href;
    const isValidImg = !imageUrl.endsWith('undefined');
    if (!isValidImg && !isValidThumb) {
      continue;
    }

    const galleryFilePath = join(basePath, provider, 'gallery');
    await mkdir(galleryFilePath, { recursive: true });

    // Download Thumbnail and convert to webp
    const filePathName = join(galleryFilePath, `img-${photoNumber}`)
    if (isValidThumb) {
      tabinfo(`Downloading thumbnail for image ${photoNumber}`);
      const thumbnailFilePath = filePathName + '-thumb.webp';
      await sharp(await downloadImage(thumbnailUrl)).webp({
        quality: 100,
        lossless: true
      }).resize(WIDTH, HEIGHT, {
        fit: 'cover',
      }).toFile(thumbnailFilePath);
    }

    // Download Full size image and convert to webp
    if (isValidImg) {
      tabinfo(`Downloading image ${photoNumber}`);
      const imageFilePath = filePathName + '.webp';
      await sharp(await downloadImage(imageUrl)).webp({
        quality: 100,
        lossless: true
      }).toFile(imageFilePath);
    }
  }

  console.info(`Provider ${provider} completed`);
}
import * as glob from 'glob';
import sharp from 'sharp';
import { join, dirname, extname, basename } from 'path';
import { rm } from 'fs/promises';

// Usamos la función 'glob.sync' para obtener todos los archivos .jpg, .jpeg, .png en el directorio especificado
const files = glob.sync('public/**/*.{jpg,jpeg,png}');

const shouldRemove = (str: string = '') => str.toLowerCase().startsWith('rm');
const remove = shouldRemove(process.argv[2]?.toLowerCase());

for (const file of files) {
  console.info(`Converting ${file}`);
  
  const replaceExtWithDot = (newExtWithDot: string, inFilePath: string) => join(dirname(inFilePath), basename(inFilePath, extname(inFilePath))) + newExtWithDot;
  const newFilePath = replaceExtWithDot('.webp', file);

  // Usamos sharp para convertir la imagen a webp con una calidad del 100%
  const convert = sharp(file)
    .webp({
      lossless: true,
      quality: 100
    });

  // Guardamos la nueva imagen en el archivo especificado
  await convert.toFile(newFilePath);
  
  console.info(`Converted to ${newFilePath}`);

  if (remove) {
    console.log(`Removing old file ${file}`)
    await rm(file, { force: true })
  }
}

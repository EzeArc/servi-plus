import * as glob from 'glob';
import sharp from 'sharp';
import { join, dirname, basename } from 'path';

// Usamos la función 'glob' para obtener todos los archivos .webp en el directorio especificado
const files = glob.sync('public/archivo-page/**/gallery/*.{webp}');

for (const file of files) {
  console.info(`Converting ${file}`);
  
  // Creamos la ruta del nuevo archivo en el subdirectorio 'thumbnails'
  const newFilePath = join(dirname(file), 'thumbnails', basename(file));

  // Usamos sharp para convertir la imagen a webp con una calidad del 25%
  const convert = sharp(file)
    .webp({
      lossless: false,
      quality: 25
    });

  // Guardamos la nueva imagen en el archivo especificado
  await convert.toFile(newFilePath);
  
  console.info(`Converted to ${newFilePath}`);
}

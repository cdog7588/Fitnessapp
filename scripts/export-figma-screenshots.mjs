#!/usr/bin/env node
/**
 * Exports all NeuralFit design frames from Figma as PNG screenshots.
 *
 * Usage:
 *   FIGMA_TOKEN=your_personal_access_token node scripts/export-figma-screenshots.mjs
 *
 * Get a token at: https://www.figma.com/developers/api#access-tokens
 */

import { writeFileSync, mkdirSync, existsSync } from 'fs';
import { join } from 'path';

const FILE_KEY = 'Q8Q4RnR7XK2SrMFHm7tTR5';
const TOKEN = process.env.FIGMA_TOKEN;
const OUT_DIR = join(process.cwd(), 'design-screenshots');
const SCALE = 2; // 2x for retina-quality reference images

if (!TOKEN) {
  console.error('Missing FIGMA_TOKEN. Get one at https://www.figma.com/developers/api#access-tokens');
  console.error('Usage: FIGMA_TOKEN=<token> node scripts/export-figma-screenshots.mjs');
  process.exit(1);
}

const FRAMES = [
  // Desktop Auth
  { id: '8:9',    name: '01-desktop-login' },
  { id: '8:40',   name: '02-desktop-register' },
  // Desktop Core
  { id: '8:77',   name: '03-desktop-dashboard' },
  { id: '8:235',  name: '04-desktop-exercises' },
  { id: '8:350',  name: '05-desktop-plans' },
  { id: '8:445',  name: '06-desktop-plan-detail' },
  // Desktop Analytics
  { id: '8:676',  name: '07-desktop-workout-generator' },
  { id: '8:801',  name: '08-desktop-workout-logger' },
  { id: '8:988',  name: '09-desktop-analytics' },
  { id: '8:1124', name: '10-desktop-settings' },
  { id: '8:1216', name: '11-desktop-profile' },
  // Mobile
  { id: '8:1337', name: '12-mobile-login' },
  { id: '8:1377', name: '13-mobile-register' },
  { id: '8:1422', name: '14-mobile-dashboard' },
  { id: '8:1524', name: '15-mobile-exercises' },
  { id: '8:1621', name: '16-mobile-plans' },
  { id: '8:1704', name: '17-mobile-plan-detail' },
  { id: '8:1791', name: '18-mobile-generator' },
  { id: '8:1841', name: '19-mobile-workout-logger' },
  { id: '8:1935', name: '20-mobile-analytics' },
  // Popups & States
  { id: '8:2134', name: '21-confirmation-dialogs' },
  { id: '8:2187', name: '22-toast-notifications' },
  { id: '8:2233', name: '23-loading-empty-states' },
  { id: '8:2293', name: '24-contextual-micro-ui' },
];

async function fetchFigmaImages(nodeIds) {
  const ids = nodeIds.join(',');
  const url = `https://api.figma.com/v1/images/${FILE_KEY}?ids=${ids}&format=png&scale=${SCALE}`;
  const res = await fetch(url, {
    headers: { 'X-Figma-Token': TOKEN },
  });
  if (!res.ok) {
    const text = await res.text();
    throw new Error(`Figma API error ${res.status}: ${text}`);
  }
  const json = await res.json();
  if (json.err) throw new Error(`Figma API: ${json.err}`);
  return json.images;
}

async function downloadImage(url, filepath) {
  const res = await fetch(url);
  if (!res.ok) throw new Error(`Download failed: ${res.status}`);
  const buffer = Buffer.from(await res.arrayBuffer());
  writeFileSync(filepath, buffer);
  return buffer.length;
}

async function main() {
  if (!existsSync(OUT_DIR)) mkdirSync(OUT_DIR, { recursive: true });

  console.log(`Fetching image URLs for ${FRAMES.length} frames...`);

  // Figma API accepts up to 50 IDs per request
  const nodeIds = FRAMES.map(f => f.id);
  const images = await fetchFigmaImages(nodeIds);

  console.log(`Got ${Object.keys(images).length} image URLs. Downloading...\n`);

  let downloaded = 0;
  for (const frame of FRAMES) {
    const imageUrl = images[frame.id];
    if (!imageUrl) {
      console.log(`  SKIP  ${frame.name} (no image URL returned)`);
      continue;
    }
    const filepath = join(OUT_DIR, `${frame.name}.png`);
    const size = await downloadImage(imageUrl, filepath);
    downloaded++;
    console.log(`  SAVED ${frame.name}.png (${(size / 1024).toFixed(1)} KB)`);
  }

  console.log(`\nDone! ${downloaded}/${FRAMES.length} screenshots saved to ${OUT_DIR}/`);
  console.log('\nTo push to GitHub:');
  console.log('  git add design-screenshots/');
  console.log('  git commit -m "Add Figma design screenshots"');
  console.log('  git push');
}

main().catch(err => {
  console.error('Error:', err.message);
  process.exit(1);
});

export const colors = {
  bg: {
    primary: '#09090B',
    secondary: '#141416',
    tertiary: '#1D1D21',
    quaternary: '#27272D',
  },
  border: {
    default: '#2A2A32',
    accent: '#CCFF00',
    error: '#FF3B30',
    black: '#000000',
  },
  text: {
    primary: '#FFFFFF',
    secondary: '#9E9EA8',
    tertiary: '#5F5F69',
  },
  accent: {
    primary: '#CCFF00',
    data: '#00E5FF',
  },
  semantic: {
    error: '#FF3B30',
    success: '#34C759',
    warning: '#FFD60A',
  },
  overlay: 'rgba(0, 0, 0, 0.6)',
} as const;

export const fonts = {
  display: "'Unbounded', sans-serif",
  body: "'Geist', sans-serif",
  mono: "'Geist Mono', monospace",
} as const;

export const typography = {
  display:        { family: fonts.display, size: 24, weight: 900 },
  headingLg:      { family: fonts.display, size: 20, weight: 800 },
  headingMd:      { family: fonts.display, size: 16, weight: 800 },
  headingSm:      { family: fonts.display, size: 14, weight: 800 },
  headingXs:      { family: fonts.display, size: 12, weight: 800 },
  bodyMd:         { family: fonts.body,    size: 14, weight: 500 },
  bodySm:         { family: fonts.body,    size: 13, weight: 400 },
  bodySemibold:   { family: fonts.body,    size: 14, weight: 600 },
  bodyBold:       { family: fonts.body,    size: 14, weight: 700 },
  monoMd:         { family: fonts.mono,    size: 14, weight: 400 },
  monoSm:         { family: fonts.mono,    size: 13, weight: 400 },
  monoXs:         { family: fonts.mono,    size: 11, weight: 400 },
  monoXxs:        { family: fonts.mono,    size: 10, weight: 400 },
  monoBold:       { family: fonts.mono,    size: 14, weight: 700 },
  monoBoldXs:     { family: fonts.mono,    size: 11, weight: 700 },
} as const;

export const spacing = {
  1:  4,
  2:  6,
  3:  8,
  4:  10,
  5:  12,
  6:  14,
  7:  16,
  8:  20,
  9:  24,
  10: 28,
  11: 32,
  12: 40,
  13: 64,
} as const;

export const radius = {
  xs:      2,
  sm:      4,
  md:      6,
  default: 8,
  lg:      12,
  xl:      16,
  '2xl':   18,
  full:    9999,
} as const;

export const zIndex = {
  base:          0,
  sidebar:       10,
  topbar:        20,
  sticky:        30,
  dropdown:      40,
  toast:         50,
  modalBackdrop: 60,
  modal:         70,
  tooltip:       80,
} as const;

export const layout = {
  sidebarWidth:   260,
  topbarHeight:   56,
  bottomTabHeight: 56,
  authCardWidth:  440,
} as const;

export const breakpoints = {
  mobile:    768,
  tabletSm:  1024,
  desktop:   1440,
} as const;

export const transitions = {
  fast:     '100ms ease',
  default:  '150ms ease',
  medium:   '200ms ease-in-out',
  slow:     '300ms ease-out',
  skeleton: '1500ms ease-in-out infinite',
} as const;

// Button specs
export const button = {
  primary:     { bg: colors.accent.primary, text: '#000000', border: 'none' },
  secondary:   { bg: 'transparent',         text: colors.text.primary, border: `1px solid ${colors.border.default}` },
  destructive: { bg: colors.semantic.error,  text: colors.text.primary, border: 'none' },
  ghost:       { bg: 'transparent',         text: colors.text.secondary, border: 'none' },
  icon:        { bg: colors.bg.tertiary,    text: colors.text.primary, border: `1px solid ${colors.border.default}` },
  sizes: {
    lg: { height: 48, padding: '16px 24px' },
    md: { height: 40, padding: '12px 20px' },
    sm: { height: 34, padding: '8px 16px' },
  },
} as const;

// Input specs
export const input = {
  height: 48,
  bg: colors.bg.tertiary,
  border: `1px solid ${colors.border.default}`,
  borderFocus: `1px solid ${colors.border.accent}`,
  borderError: `1px solid ${colors.border.error}`,
  radius: radius.default,
  padding: '12px 16px',
  labelFont: { family: fonts.mono, size: 11, weight: 400, color: colors.text.secondary },
} as const;

// Card specs
export const card = {
  bg: colors.bg.tertiary,
  border: `1px solid ${colors.border.default}`,
  hoverBg: colors.bg.quaternary,
  variants: {
    auth:     { width: 440, padding: 32, radius: radius.xl },
    exercise: { padding: 20, radius: radius.lg },
    plan:     { padding: 24, radius: radius.xl },
    stat:     { padding: 20, radius: radius.lg },
    day:      { padding: '20px 24px', radius: radius.lg },
  },
} as const;

// Toast specs
export const toast = {
  width: 360,
  bg: colors.bg.tertiary,
  border: `1px solid ${colors.border.default}`,
  radius: radius.default,
  padding: 16,
  position: { top: 24, right: 24 },
  autoDismiss: { success: 5000, warning: 8000, error: null },
  variants: {
    success: { accent: colors.semantic.success },
    error:   { accent: colors.semantic.error },
    warning: { accent: colors.semantic.warning },
  },
} as const;

// Modal specs
export const modal = {
  maxWidth: 480,
  bg: colors.bg.tertiary,
  border: `1px solid ${colors.border.default}`,
  radius: radius.xl,
  padding: 32,
  backdrop: colors.overlay,
} as const;

export const theme = {
  colors,
  fonts,
  typography,
  spacing,
  radius,
  zIndex,
  layout,
  breakpoints,
  transitions,
  button,
  input,
  card,
  toast,
  modal,
} as const;

export type Theme = typeof theme;
export default theme;

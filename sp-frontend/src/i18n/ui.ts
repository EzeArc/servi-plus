import ArgentinaFlag from '@/components/flags/Argentina.astro';
import UnitedStatesFlag from '@/components/flags/UnitedStates.astro';

// Add missing imports
export const LANGUAGES: Record<
	string,
	{ code: string; name: string; flag: typeof ArgentinaFlag }
> = {
	en: {
		code: 'en',
		name: 'English',
		flag: UnitedStatesFlag,
	},
	es: {
		code: 'es',
		name: 'Español',
		flag: ArgentinaFlag,
	},
};

export const defaultLang = 'es';
export const showDefaultLang = false;

export const ui = {
	es: {
		'nav.inicio': 'Inicio',
		'nav.servicios': 'Servicios',
		'nav.info': 'Información',
		'nav.usuario': 'Usuario',
		'nav.legal': 'Aviso Legal',
		'nav.privacidad': 'Privacidad',
		'nav.cookies': 'Cookies',
	},
	en: {
		'nav.inicio': 'Home',
		'nav.services': 'Services',
		'nav.info': 'Information',
		'nav.user': 'User',
		'nav.legal': 'Legal Notice',
		'nav.privacidad': 'Privacy',
		'nav.cookies': 'Cookies',
	},
} as const;

export const routes = {
	es: {
		servicio: 'servicios',
		info: 'info',
		usuario: 'usuario',
		'aviso-legal': 'aviso-legal',
		privacidad: 'privacidad',
		cookies: 'cookies',
	},
	en: {
		services: 'services',
		info: 'information',
		user: 'user',
		'aviso-legal': 'legalnotice',
		privacidad: 'privacy',
		cookies: 'cookies',
	},
};
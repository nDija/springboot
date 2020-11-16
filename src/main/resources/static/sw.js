importScripts('https://www.gstatic.com/firebasejs/7.0.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.0.1/firebase-messaging.js');

var firebaseConfig = {
	apiKey: "AIzaSyB2f7vqbN7UW2YW5WArsmtYgYmwpbHRxWo",
	authDomain: "webpushtest-244f7.firebaseapp.com",
	databaseURL: "https://webpushtest-244f7.firebaseio.com",
	projectId: "webpushtest-244f7",
	storageBucket: "webpushtest-244f7.appspot.com",
	messagingSenderId: "793772654275",
	appId: "1:793772654275:web:42e285d55230370a68ec76"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
messaging.usePublicVapidKey('BL0YYH-fklkSZCJ41MhDmWWAc0FG7y9iWf1hSgvWQAr5bu6zNSXfmsQGDYzKrk_yPnToNJCUmDRLvtQH6efq6aU');

self.addEventListener('push', async event => {
	const db = await getDb();
	let tx = this.db.transaction('jokes', 'readwrite');
	let store = tx.objectStore('jokes');

	const data = event.data.json().data;
	data.id = parseInt(data.id);

	tx.oncomplete = async e => {
		store.put(data);
		const allClients = await clients.matchAll({ includeUncontrolled: true });
		for (const client of allClients) {
			client.postMessage('newData');
		}
	};
});

async function getDb() {
	if (this.db) {
		return Promise.resolve(this.db);
	}

	return new Promise(resolve => {
		const openRequest = indexedDB.open("Chuck", 1);

		openRequest.onupgradeneeded = event => {
			const db = event.target.result;
			db.createObjectStore('jokes', { keyPath: 'id' });
		};

		openRequest.onsuccess = event => {
			this.db = event.target.result;
			resolve(this.db);
		}
	});
}


messaging.setBackgroundMessageHandler(function(payload) {
  const notificationTitle = 'Background Title (client)';
  const notificationOptions = {
    body: 'Background Body (client)',
    icon: '/mail.png'
  };

  return self.registration.showNotification(notificationTitle,
      notificationOptions);
});


const CACHE_NAME = 'my-site-cache-v1';
const urlsToCache = [
	'/index.html',
	'/index.js',
	'/mail.png',
	'/mail2.png',
	'/manifest.json'
];

self.addEventListener('install', event => {
	event.waitUntil(caches.open(CACHE_NAME)
		.then(cache => cache.addAll(urlsToCache)));
});

self.addEventListener('fetch', event => {
	event.respondWith(
		caches.match(event.request)
			.then(response => {
				if (response) {
					return response;
				}
				return fetch(event.request);
			}
			)
	);
});



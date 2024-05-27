async function postData(url='',data={}) {
	const reponse = await fetch(url, {
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json'
		},
		body : JSON.stringify(data)
	});
	return reponse.json();
}